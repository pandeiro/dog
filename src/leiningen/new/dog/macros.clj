(ns {{sanitized}}.macros
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

(defmacro set-window-onload!
  "In development, takes care not to clobber 
window.onload handlers already bound, enabling ring-refresh
to work properly.

In production, when compiling with this invocation --

ENV=production lein cljsbuild once production

-- simply sets window.onload to function f."
  [f]
  (let [env (System/getenv "ENV")]
    (if (= env "production")
      `(set! (.-onload js/window) ~f) 
      `(let [onload# (.-onload js/window)]
         (set! (.-onload js/window)
               (if (fn? onload#)
                 (fn [] (onload#) (~f))
                 ~f))))))
