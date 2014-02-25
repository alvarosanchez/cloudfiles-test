package com.odobo.web.filestorage

import com.google.inject.AbstractModule
import com.google.inject.Scopes

/**
 * TODO: write doc
 */
class CloudFilesModule extends AbstractModule {

    /**
     * Configures a {@link Binder} via the exposed methods.
     */
    @Override
    protected void configure() {
        bind(CloudFilesService).in(Scopes.SINGLETON)
    }
}
