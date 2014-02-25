package com.odobo.web.filestorage

import groovy.transform.Immutable

/**
 * TODO: write doc
 */
@Immutable
class CloudFile {

    String name

    String contentType

    URI uri

    Date lastModified

    String container

    Long bytes

}
