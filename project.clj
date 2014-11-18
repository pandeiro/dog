(defproject dog/lein-template "0.5.13"
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
                 [org.webjars/react "0.11.1"]]

  :license {:name "Public Domain"}

  :eval-in-leiningen true)
