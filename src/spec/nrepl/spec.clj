(ns nrepl.spec
  (:require [clojure.spec.alpha :as s]))

(s/def ::op keyword?)

(s/def ::code string?)

;; Should be uuid string?
(s/def ::id string?)

(s/def ::message (s/keys :opt-un [::op ::code]))
