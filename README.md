# dog

A Leiningen template for ClojureScript applications

## Design goals

### For Development

- Rapid development cycle including live reloading on file changes
- Built-in [Ring dev server](https://github.com/weavejester/lein-ring)
- Built-in ClojureScript libs [core.async](https://clojure.github.io/core.async/) and [reagent](https://github.com/holmsand/reagent) (ReactJS)
- Built-in [LessCSS](http://lesscss.org/) compilation in development (in-browser) and production (lessc)
- Built-in client-side routing with [secretary](https://github.com/gf3/secretary) and [Google Closure History](http://docs.closure-library.googlecode.com/git/class_goog_History.html)
- Built-in [EDN-compatible XHR requests](https://github.com/pandeiro/dog/blob/master/src/leiningen/new/dog/xhr.cljs)

### For Deployment

- Environment-sensitive URL configurability via a config file
- Automated production builds with minification of HTML, ClojureScript and Less

## External Dependencies

- [Leiningen](https://github.com/technomancy/leiningen)
- [Less.js](http://lesscss.org/) CSS preprocessor (for production compilation only)

(JavaScript dependencies, ReactJS and Less.js, are bundled with the template.)

## Usage

### Development

To create a new project and start the live development
web server and ClojureScript auto-compilation:

    lein new dog mywebapp

### Output

The following project structure will be generated:

    .
    ├── app
    │   ├── index.html
    │   ├── styles
    │   │   ├── main.less
    │   │   └── normalize.css
    │   └── vendor
    │       ├── less.js
    │       └── react.js
    ├── bin
    │   ├── dev-server
    │   └── make-dist
    ├── config.edn.sample
    ├── dist
    │   └── index.html
    ├── project.clj
    └── src
        ├── clj
        │   └── mywebapp
        │       ├── dev_server.clj
        │       ├── html.clj
        │       └── macros.clj
        └── cljs
            └── mywebapp
                ├── app.cljs
                └── util
                    ├── routing.cljs
                    └── xhr.cljs

### Beginning

If you plan to use configurable URLs that are different in development
and production, you should first copy `config.edn.sample` to `config.edn`
(which will be gitignored).

    cd mywebapp
    ./bin/dev-server

This will serve the current project at http://localhost:3000,
auto-compile ClojureScript and auto-refresh on changes to the
`app` directory.

### Production

To package a project into production assets:

    ./bin/make-dist

This results in the following minified files:

- dist/index.html (and minified versions of any other HTML files found in app/)
- dist/app.js
- dist/styles.css

...and a tarball artifact, in case that helps:

- target/mywebapp.tar.gz

## License

Released unencumbered into the public domain
