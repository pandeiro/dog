(ns {{name}}.html
  (:require [clojure.java.io        :as io]
            [clojure.string         :as s]
            [clojure.java.shell     :refer [sh]]
            [net.cgrand.enlive-html :as enlive])
  (:import [com.googlecode.htmlcompressor.compressor HtmlCompressor]))

(defn- has-git? [] (= 0 (:exit (sh "which" "git"))))
(defn- git-rev [] (s/trim (:out (sh "git" "rev-parse" "--short" "HEAD"))))
(defn- html-file? [f] (and (.isFile f) (.endsWith (s/lower-case (.getName f)) "html")))

(defn tagged
  "Adds a querystring from git rev for cache-busting"
  [x tag]
  (str x (when tag (str "?ref=" tag))))

(defn transform-html
  "Replaces script tags and LessCSS links with distribution versions"
  [html tag]
  (let [script [:script {:src (tagged "app.js" tag)}]
        link   [:link   {:type "text/css", :rel "stylesheet"
                         :href (tagged "styles.css" tag)}]
        append (fn [x]
                 (-> x enlive/html enlive/append))]
    (-> html
      (enlive/sniptest [:script] nil)
      (enlive/sniptest [:body] (append script))
      (enlive/sniptest [(enlive/attr= :rel "stylesheet/less")] nil)
      (enlive/sniptest [:head] (append link)))))

(defn minify-html
  "Uses https://code.google.com/p/htmlcompressor/ to minify HTML"
  [html]
  (doto (HtmlCompressor.)
    (.setRemoveIntertagSpaces true)
    (.compress html)))


(defn -main
  "Transforms and minifies any HTML assets"
  [& args]
  (let [html-files (->> "app" io/file file-seq (filter html-file?))
        tag        (when (has-git?) (git-ref))]
    (doseq [file html-files]
      (let [filename (.getName file)
            content  (slurp file)]
        (println "Transforming and minifying" filename "...")
        (spit (str "dist/" filename)
              (minify-html (transform-html content tag)))))
    (System/exit 0)))
