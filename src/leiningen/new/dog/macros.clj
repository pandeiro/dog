(ns {{name}}.macros
  (:require [clojure.edn :as edn]))

(defmacro resolve-config
  "Allows for a static configuration file with, eg
different URLs for use with development and production
environment services."
  []
  (let [config (edn/read-string (slurp "config.edn"))
        env    (System/getenv "ENV")]
    (if (= env "production")
      (:production config)
      (:development config))))


