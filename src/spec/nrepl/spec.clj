(ns nrepl.spec
  (:require [clojure.spec.alpha :as s]))

(s/def ::op keyword?)

(s/def ::code string?)

(s/def ::id string?)

;; should be uuid strings
(s/def ::session string?)

(s/def ::new-session string?)

(s/def ::verbose? boolean?)

(s/def ::doc string?)

(s/def ::requires (s/map-of keyword? string?))

(s/def ::optional (s/map-of keyword? string?))

(s/def ::returns (s/map-of keyword? string?))

(s/def ::description (s/keys :opt-un [::doc ::requires ::optional ::returns]))

(s/def ::ops (s/map-of keyword? ::description))

(s/def ::major (s/or :integer int?
                     :string  string?))

(s/def ::minor (s/or :integer int?
                     :string  string?))

(s/def ::incremental (s/or :integer int?
                           :string  string?))

(s/def ::version (s/keys :opt-un [::major ::minor ::incremental ::version-string]))

(s/def ::versions (s/map-of keyword? ::version))

(s/def ::out string?)

;; Value printing breaks this.. Otherwise string works
(s/def ::value some?)

(s/def ::line int?)

(s/def ::column int?)

;; Single a single status is valid?
(s/def ::status (s/coll-of keyword? :kind? set?))

(s/def ::message (s/keys :opt-un [::op ::code ::out ::value ::status
                                  ::session ::line ::column ::new-session ::ops ::verbose?
                                  ::versions]))
