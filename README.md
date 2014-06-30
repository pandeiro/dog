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
- Semi-automated* production builds with minification

(There is no automated HTML file generation; for now, the template simply
generates both development and production versions of index.html. Any changes to the
HTML skeleton must be propagated manually from app/index.html to dist/index.html if you
are packaging for distribution.)

ClojureScript compilation and minification are automated, except for external JavaScript
libraries, which should be added to the relevant :preamble vectors in the
production cljsbuild within project.clj.)

## External Dependencies

- [Leiningen](https://github.com/technomancy/leiningen)
- [Less.js](http://lesscss.org/) CSS preprocessor (for production compilation only)

(JavaScript dependencies, ReactJS and Less.js, are bundled with the template.)

## Usage

### Development

To create a new project and start the live development
web server and ClojureScript auto-compilation:

    lein new dog mywebapp
    cd mywebapp
    ./bin/dev-server

Open up src/cljs/mywebapp/app.cljs and begin hacking. It will look something
like this:

#### Namespace declaration

```clojure
(ns mywebapp.app
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [testy.macros :refer [resolve-config set-window-onload!]])
  (:require [cljs.core.async :as async :refer [chan put! <! >!]]
            [reagent.core :as r :refer [render-component]]
            [testy.util.routing :refer [defroute enable-routes]]
            [testy.util.xhr :as xhr :refer [get-edn post-edn! put-edn!]]))
```

#### Static configuration

```clojure
;; --- Config (imports config.edn)
(def config (resolve-config))
```

Placing your configuration in a file called `config.edn`, containing a map
with :development and :production keys, allows the client-side application to
use different static configuration depending on its environment. The
`./bin/make-dist` script will automatically configure this data using the map
in :production.

#### Application state and routing

```clojure
;; --- State
(def app-state (r/atom {:view :main}))

;; --- Routes
(defroute "/main" []
  (swap! app-state assoc :view :main))

(defroute "/options" []
  (swap! app-state assoc :view :options))

(enable-routes)
```

#### Views

```clojure
;; --- Views
(def main-view [:div#main [:h1 "Main View"]])

(def options-view [:div#options [:h1 "Options View"]])

(defn render-app []
  (let [wrapper (.getElementById js/document "wrapper")]
    (render-component (condp (:view @app-state) =
                        :main    [main-view]
                        :options [options-view])
                      wrapper)))
```

#### Entry

```clojure
(defn init
  "A single entrypoint for the application"
  []
  (render-app))

(.addEventListener js/window "DOMContentLoaded" init)
```

On saving any file in `src/` or `app/`, ClojureScript will be re-compiled incrementally
and the browser will refresh.

### Production

To package a project into production assets:

    ./bin/make-dist

This will result in two compiled, minified files being added
to the dist/ folder:

- app.js
- styles.css

Remember that any changes to app/index.html will not be reflected
in dist/index.html -- transporting those is up to you, for now.

## License

Released unencumbered into the public domain
