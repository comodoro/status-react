(ns status-im.data-store.realm.schemas.base.v2.bootnode)

(def schema {:name       :bootnode
             :primaryKey :id
             :properties {:id      :string
                          :name    {:type     :string}
                          :chain   {:type     :string}
                          :address {:type     :string}}})
