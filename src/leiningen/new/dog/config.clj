(ns {{name}}.config
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defmacro resolve
  "Allows for a static configuration file with, eg
different URLs for use with development and production
environment services."
  []
  (let [cfg-file (io/file "config.edn")
        config   (if (.exists cfg-file)
                   (edn/read-string (slurp cfg-file))
                   {})
        env      (System/getenv "ENV")]
    (if (= env "production")
      (:production config {})
      (:development config {}))))


