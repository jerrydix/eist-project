import {defineStore} from 'pinia'

export const userStore = defineStore('userStore', {
    state: () => {
        return {
            // all these properties will have their type inferred automatically
            username: window.localStorage.getItem("user")
        }
    },
})