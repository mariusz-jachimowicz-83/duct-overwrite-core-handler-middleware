(ns duct.module.overwrite-core-handler-middleware-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [duct.core :as duct]
   [duct.logger :as logger]
   [fipp.edn :refer [pprint]]
   [integrant.core :as ig]))

(duct/load-hierarchy)

(defrecord NoOpLogger []
  logger/Logger
  (-log [_ level ns-str file line id event data]))

;; fake logger initialization
;; we don't need whole logger subsystem
(defmethod ig/init-key :duct/logger [_ config] (->NoOpLogger))

(def base-config
  {::duct/environment :development
   :duct/logger {}
   :duct.module.web/api {}

   :duct.module/overwrite-core-handler-middleware
   {:development {:middleware ^:distinct [(ig/ref :duct.middleware.web/not-found)]}
    :production {:middleware ^:distinct [] [(ig/ref :duct.middleware.web/defaults)]}
    :testing {:middleware ^:distinct [] [(ig/ref :duct.middleware.web/log-requests)]}}})

(deftest configuration-test
  (testing "duct core handler middlewares array is overwriten based on environment"
    (println "== test cfg result")
    (is (= {}
           (duct/prep base-config)))))
