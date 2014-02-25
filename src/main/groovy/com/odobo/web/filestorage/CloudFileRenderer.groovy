package com.odobo.web.filestorage

import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.render.GroovyRendererSupport
import ratpack.jackson.Jackson

/**
 * TODO: write doc
 */
class CloudFileRenderer extends GroovyRendererSupport<CloudFile> {

    /**
     * Renders the given object to the context.
     *
     * @see ratpack.render.Renderer#render(ratpack.handling.Context, Object)
     * @param context The context to render to
     * @param object The object to render
     * @throws Exception If the object cannot be rendered for any reason
     */
    @Override
    void render(GroovyContext context, CloudFile file) throws Exception {
        context.byContent {
            json {
                render Jackson.json(file)
            }
        }
    }
}
