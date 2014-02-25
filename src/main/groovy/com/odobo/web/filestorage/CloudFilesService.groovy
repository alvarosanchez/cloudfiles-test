package com.odobo.web.filestorage

import groovy.util.logging.Slf4j
import org.jclouds.blobstore.domain.PageSet
import org.jclouds.openstack.swift.domain.ObjectInfo
import ratpack.rx.RxBackground

import org.jclouds.ContextBuilder
import org.jclouds.blobstore.BlobStore
import org.jclouds.blobstore.BlobStoreContext
import org.jclouds.openstack.swift.CommonSwiftAsyncClient
import org.jclouds.openstack.swift.CommonSwiftClient
import org.jclouds.rest.RestContext

import javax.inject.Inject

/**
 * TODO: write doc
 */
@Slf4j
class CloudFilesService {

    final RxBackground rxBackground

    BlobStoreContext context

    BlobStore blobStore

    RestContext<CommonSwiftClient, CommonSwiftAsyncClient> swift

    CommonSwiftClient cloudFilesClient

    @Inject
    CloudFilesService(RxBackground rxBackground) {
        println System.getenv('CF_USERNAME')
        println System.getenv('CF_PASSWORD')

        this.rxBackground = rxBackground
        this.context = ContextBuilder.newBuilder('cloudfiles-uk')
                .credentials(System.getenv('CF_USERNAME'), System.getenv('CF_PASSWORD'))
                .buildView(BlobStoreContext.class)
        this.blobStore = context.blobStore
        this.swift = context.unwrap()
        this.cloudFilesClient = this.swift.api
    }

    rx.Observable<CloudFile> list(String container) {

        rxBackground.observeEach {
            PageSet<ObjectInfo> objects = this.cloudFilesClient.listObjects(container)
            return objects
        }.map {
            new CloudFile(name: it.name, bytes: it.bytes, container: it.container, contentType: it.contentType, lastModified: it.lastModified)
        }
    }
}
