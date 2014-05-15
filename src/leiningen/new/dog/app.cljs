(ns {{sanitized}}.app
  (:require-macros [{{sanitized}}.macros :refer [resolve-config set-window-onload!]]
                   [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :as async :refer [chan put! <!]]
            [reagent.core :as r :refer [render-component]]))

(def config (resolve-config))

;;
;; Application state and functions typically go here
;;

(defn init
  "A single entrypoint for the application"
  []
  )

(set-window-onload! init)


