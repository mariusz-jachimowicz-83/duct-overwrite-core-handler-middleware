(defproject com.mjachimowicz/duct-overwrite-core-handler-middleware "0.1.0"
  :description "Very simple sql db module for Duct framework"
  :url         "https://github.com/mariusz-jachimowicz-83/duct-overwrite-core-handler-middleware"
  :license     {:name "Eclipse Public License"
                :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [duct/module.web "0.6.4"]]

  :deploy-repositories [["clojars" {:sign-releases false}]]

  ;; lein cloverage --fail-threshold 95
  ;; lein kibit
  ;; lein eastwood
  :profiles {:dev {:plugins [[lein-cloverage  "1.0.10"]
                             [lein-kibit      "0.1.6"]
                             [jonase/eastwood "0.2.5"]]}})
