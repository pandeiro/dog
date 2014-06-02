(ns {{sanitized}}.util.xhr
  (:require [cljs.reader :refer [read-string]]
            [goog.net.XhrIo :as xhr]))

(defn- response->edn [e]
  (read-string (.getResponse (.-target e))))


(defn get-edn [url f]
  (xhr/send url #(f (response->edn %))))

(defn put-edn! [url data f]
  (xhr/send url "PUT" nil nil #(f (response->edn %))))

(defn post-edn! [url data f]
  (xhr/send url "POST" nil nil #(f (response->edn %))))
