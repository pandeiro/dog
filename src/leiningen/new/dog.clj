(ns leiningen.new.dog
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]
            [clojure.java.io :as io]))

(def render (renderer "dog"))

(defn dog
  "Output the template"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}
        less-jar-path "META-INF/resources/webjars/less/1.7.3/less.js"
        react-jar-path "META-INF/resources/webjars/react/0.11.1/"]
    (main/info (str "Generated a new ClojureScript project in " (:sanitized data)))
    (->files
     data
     ["project.clj"                          (render "project.clj" data)]
     ["config.edn"                           (render "config.edn" data)]
     [".gitignore"                           (render "gitignore" data)]

     ["src/clj/{{sanitized}}/dev/server.clj" (render "dev_server.clj" data)]
     ["src/clj/{{sanitized}}/dev/html.clj"   (render "html.clj" data)]
     ["src/clj/{{sanitized}}/dev/repl.clj"   (render "repl.clj" data)]
     ["src/clj/{{sanitized}}/config.clj"     (render "config.clj" data)]

     ["src/cljs/{{sanitized}}/app.cljs"      (render "app.cljs" data)]

     ["app/styles/normalize.css"             (render "normalize.css")]
     ["app/styles/main.less"                 (render "main.less")]
     ["app/vendor/less.js"                   (io/reader (io/resource less-jar-path))]
     ["app/vendor/react.js"                  (io/reader (io/resource (str react-jar-path "react.js")))]
     ["app/vendor/react.min.js"              (io/reader (io/resource (str react-jar-path "react.min.js")))]
     ["app/index.html"                       (render "app/index.html" data)]
     "dist"
     ["bin/make-dist"                        (render "make-dist.bash" data)  :executable true])
    (do
      (main/info "")
      (main/info "Development:  $ lein dev")
      (main/info "Distribution: $ ./bin/make-dist"))))
