(ns duct.module.overwrite-core-handler-middleware
  (:require
   [clojure.pprint :refer [pprint]]
   [duct.core :as duct]
   [integrant.core :as ig]))

(defn- get-environment [config options]
  (:environment options (:duct.core/environment config :production)))

(defmethod ig/init-key :duct.module/overwrite-core-handler-middleware
  [_ options]
  {:req #{:duct.module.web/api :duct.core/handler}
   :fn (fn [config]
         (let [environment (get-environment config options)
               middlewares-cfg {:duct.core/handler (get options environment)}
               _ (println "== options")
               _ (pprint options)
               _ (println "== new stack by env")
               _ (pprint middlewares-cfg)
               _ (println "== cfg with dissoc")
               _ (pprint (update config :duct.core/handler dissoc :middleware))
               _ (println "== result of merge")
               _ (pprint (-> config
                             (update :duct.core/handler dissoc :middleware)
                             (duct/merge-configs middlewares-cfg)))]
           (-> config
               (assoc :duct.module/overwrite-core-handler-middleware {})
               (update :duct.core/handler dissoc :middleware)
               (duct/merge-configs middlewares-cfg))))})
