(ns nrepl.edn-test
  (:require [clojure.test :refer [deftest is testing]]
            [nrepl.core :as nrepl]
            [nrepl.server :as server]
            [nrepl.transport :as transport]))

(defn return-evaluation
  [message]
  (with-open [server (server/start-server :transport-fn transport/edn :port 7889)]
    (with-open [conn (nrepl/url-connect "nrepl+edn://localhost:7889")]
      (-> (nrepl/client conn 1000)
          (nrepl/message message)
          nrepl/response-values))))

(deftest edn-transport-communication
  (testing "op as a string value"
    (is (= (return-evaluation {:op "eval" :code "(+ 2 3)"})
           [5])))
  (testing "op as a keyword value"
    (is (= (return-evaluation {:op :eval :code "(+ 2 3)"})
           [5])))
  (testing "simple expressions"
    (is (= (return-evaluation {:op "eval" :code "(range 40)"})
           [(eval '(range 40))]))))
