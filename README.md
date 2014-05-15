# dog

A Leiningen template for ClojureScript applications

## Design goals

### Development

- Rapid development cycle including live reloading on file changes
- Built-in Ring dev server
- Built-in ClojureScript libs Core.async and Reagent (ReactJS)
- Built-in LessCSS compilation in development (in-browser) and production (lessc)

### Deployment

- Environment-sensitive URL configurability via a config file
- Semi-automated* production builds with minification

(There is no automated HTML file generation; for now, the template simply
generates both development and production versions of index.html. Any changes to the
HTML skeleton must be propagated manually from app/index.html to dist/index.html if you
are packaging for distribution.)

ClojureScript compilation and minification are automated, except for external JavaScript
libraries, which should be added to the relevant :preamble vectors in the
production cljsbuild within project.clj.)

## Usage

### Development

To create a new project and start the live development
web server and ClojureScript auto-compilation:

    lein new dog mywebapp
    cd mywebapp
    ./bin/dev-server

Open up src/cljs/mywebapp/app.cljs and begin hacking. On saving,
ClojureScript will be compiled and the browser will refresh.

### Production

To package a project into production assets:

    ./bin/make-dist

This will result in two compiled, minified files being added
to the dist/ folder:

- app.js
- styles.css

Remember that any changes to app/index.html will not be reflected
in dist/index.html -- transporting those is up to you for now.

## License

Released unencumbered into the public domain
