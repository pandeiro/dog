(ns leiningen.new.dog
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]
            [clojure.java.io :as io]))

(def render (renderer "dog"))

(defn dog
  "Output the template"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info (str "Generated a new ClojureScript project in " (:sanitized data)))
    (->files
     data
     ["project.clj"                          (render "project.clj" data)]
     ["config.edn"                           (render "config.edn" data)]

     ["src/clj/{{sanitized}}/dev_server.clj" (render "dev_server.clj" data)]
     ["src/clj/{{sanitized}}/macros.clj"     (render "macros.clj" data)]
     ["src/cljs/{{sanitized}}/app.cljs"      (render "app.cljs" data)]

     ["app/styles/normalize.css"             (render "normalize.css")]
     ["app/styles/main.less"                 (render "main.less")]
     ["app/vendor/less.js"                   (io/reader (io/resource "META-INF/resources/webjars/less/1.7.0/less.js"))]
     ["app/vendor/react.js"                  (io/reader (io/resource "reagent/react.js"))]
     ["app/index.html"                       (render "index-dev.html" data)]

     ["dist/index.html"                      (render "index-production.html" data)]

     ["bin/dev-server"                       (render "dev-server.bash" data) :executable true]
     ["bin/make-dist"                        (render "make-dist.bash" data)  :executable true])
    
    (do
      (main/info "")
      (main/info "Development:")
      (main/info (str "cd " (:sanitized data)))
      (main/info "./bin/dev-server")
      (main/info "")
      (main/info "Distribution:")
      (main/info "./bin/make-dist"))))
