(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "Something that does something."

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2342"]
                 [org.clojure/core.async "0.1.338.0-5c5012-alpha"]
                 [ring/ring-core "1.2.2"]
                 [ring-refresh "0.1.1"]
                 [enlive "1.1.5"]
                 [com.googlecode.htmlcompressor/htmlcompressor "1.4"]
                 [com.cemerick/piggieback "0.1.3"]
                 [weasel "0.4.0-SNAPSHOT"]
                 [reagent "0.4.2"]]

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.11"]]

  :resources-paths ["app"]

  :ring {:handler {{name}}.dev.server/handler}

  :repl-options {:init-ns {{name}}.dev.repl}

  :cljsbuild
  {:builds [{:id           "dev"
             :source-paths ["src/clj" "src/cljs"]
             :compiler     {:output-to     "app/{{name}}.js"
                            :output-dir    "app/out"
                            :optimizations :none
                            :source-map    true}}
            {:id           "production"
             :source-paths ["src/clj" "src/cljs" "app/vendor"]
             :compiler     {:output-to        "dist/app.js"
                            :optimizations    :advanced
                            :pretty-print     false
                            :externs          ["react.js"]
                            :preamble         ["react.min.js"]
                            :closure-warnings {:externs-validation :off
                                               :non-standard-jsdoc :off}}}]})
