import com.odobo.web.filestorage.CloudFile
import com.odobo.web.filestorage.CloudFilesModule
import com.odobo.web.filestorage.CloudFilesService
import ratpack.jackson.JacksonModule

import ratpack.rx.RxModule

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
    modules {
        register new JacksonModule()
        register new RxModule()
        register new CloudFilesModule()
    }

    handlers { CloudFilesService cloudFilesService ->

        get(':container') {

            def container = pathTokens.container

            cloudFilesService.list(container)
            .toList()
            .subscribe { List<CloudFile> files ->
                render json(files)
            }

        }
    }

}