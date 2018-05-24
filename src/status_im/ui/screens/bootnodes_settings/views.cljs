(ns status-im.ui.screens.bootnodes-settings.views
  (:require-macros [status-im.utils.views :as views])
  (:require [re-frame.core :as re-frame]
            [status-im.i18n :as i18n]
            [status-im.utils.config :as config]
            [status-im.ui.components.colors :as colors]
            [status-im.ui.components.icons.vector-icons :as vector-icons]
            [status-im.ui.components.list.views :as list]
            [status-im.ui.components.react :as react]
            [status-im.ui.components.status-bar.view :as status-bar]
            [status-im.ui.components.toolbar.view :as toolbar]
            [status-im.ui.components.toolbar.actions :as toolbar.actions]
            [status-im.ui.screens.profile.components.views :as profile.components]
            [status-im.ui.screens.bootnodes-settings.styles :as styles]))

(defn- bootnode-icon [connected?]
  [react/view (styles/bootnode-icon connected?)
   [vector-icons/icon :icons/bootnode {:color (if connected? :white :gray)}]])

(defn navigate-to-add-bootnode []
  (re-frame/dispatch [:edit-bootnode]))

(defn render-row [{:keys [name id]}]
  [react/view
   {:accessibility-label :bootnode-item}
   [react/view styles/bootnode-item
    [react/view styles/bootnode-item-inner
     [react/text {:style styles/bootnode-item-name-text}
      name]]]])

(views/defview bootnodes-settings []
  (views/letsubs [bootnodes-enabled [:settings/bootnodes-enabled]
                  bootnodes         [:settings/network-bootnodes]]
    [react/view {:flex 1}
     [status-bar/status-bar]
     [toolbar/toolbar {}
      toolbar/default-nav-back
      [toolbar/content-title (i18n/label :t/bootnodes-settings)]
      [toolbar/actions
       [(toolbar.actions/add false navigate-to-add-bootnode)]]]
     [react/view {:style {:height 50
                          :background-color "white"
                          :padding-left 15}}
      [profile.components/settings-switch-item
       {:label-kw  :t/bootnodes-enabled
        :value     bootnodes-enabled
        :action-fn #(re-frame/dispatch [:toggle-custom-bootnodes %])}]]
     [react/view styles/wrapper

      [list/flat-list {:data               (vals bootnodes)
                       :default-separator? false
                       :key-fn             :id
                       :render-fn          render-row}]]]))
