(defproject dog/lein-template "0.5.2"
  :description "A template for generating a single-page-app skeleton
that uses ClojureScript, including core.async and reagent, and the CSS
pre-processor LessCSS.

Design goals:

- Rapid development cycle including live reloading on changes
- Semi-automated production builds with minification
- Environment-sensitive URL configurability via a config file
- Basic app skeleton with routing views and ajax readily available"

  :url "https://github.com/pandeiro/dog"

  :dependencies [[org.webjars/less "1.7.3"]
                 ;; Use the React version that ships with reagent
                 [reagent "0.4.2" :exclusions [org.clojure/clojure
                                               org.clojure/clojurescript]]]

  :license {:name "Public Domain"}

  :eval-in-leiningen true)
