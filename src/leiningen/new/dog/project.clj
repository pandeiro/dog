(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "Something that does something."

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.2.2"]
                 [ring-refresh "0.1.1"]
                 [leiningen-core "2.3.4"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [org.clojure/core.async "0.1.298.0-2a82a1-alpha"]
                 [reagent "0.4.2"]]

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.10"]]

  :resources-paths ["app"]

  :ring {:handler {{sanitized}}.dev-server/handler}

  :cljsbuild
  {:builds [{:id           "dev"
             :source-paths ["src/clj" "src/cljs"]
             :compiler     {:output-to     "app/{{sanitized}}.js"
                            :output-dir    "app/out"
                            :optimizations :none
                            :source-map    true}}
            {:id           "production"
             :source-paths ["src/clj" "src/cljs"]
             :compiler     {:output-to        "dist/app.js"
                            :optimizations    :advanced
                            :externs          ["reagent/react.js"]
                            :preamble         ["reagent/react.min.js"]
                            :closure-warnings {:externs-validation :off
                                               :non-standard-jsdoc :off}}}]})
