(ns status-im.ui.screens.bootnodes-settings.db
  (:require-macros [status-im.utils.db :refer [allowed-keys]])
  (:require
   [cljs.spec.alpha :as spec]))

(spec/def ::not-blank-string (spec/and string? seq))

(spec/def :bootnode/address :not-blank-string)
(spec/def :bootnode/name ::not-blank-string)
(spec/def :bootnode/id ::not-blank-string)
(spec/def :bootnode/bootnode (allowed-keys :req-un [:bootnode/address :bootnode/name :bootnode/id]))

(spec/def :bootnodes/bootnodes (spec/nilable (spec/map-of keyword? (spec/map-of :bootnode/id :bootnode/bootnode))))
