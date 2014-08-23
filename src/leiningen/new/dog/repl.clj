(ns {{name}}.repl
  (:require
   [cemerick.piggieback   :refer [cljs-repl]]
   [weasel.repl.websocket :refer [repl-env]]))

(println)
(println "(browser-repl) ; launch ClojureScript REPL")
(println)

(defn browser-repl []
  (let [websocket-repl (repl-env :ip "0.0.0.0" :port 9001)]
    (cljs-repl :repl-env websocket-repl)))
