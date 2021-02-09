import Router from "vue-router";

export default () => {
    return new Router({
        mode: "hash",
        routes: [
            {
                path: "/oauth",
                name: "oauth",
                component: () => import( "./views/oauth/oauth.vue")
            },
            {
                path: "/home",
                name: "home",
                component: () => import( "./views/home/home.vue"),
                meta: {requireAuth: true}
            },
            {
                path: "/category",
                name: "category",
                component: () => import( "./views/category/category.vue"),
                meta: {requireAuth: true}
            },
            {
                path: "/spu",
                name: "spu",
                component: () => import( "./views/spu/spu.vue"),
                meta: {requireAuth: true}
            },
            {
                path: "/item",
                name: "item",
                component: () => import( "./views/item/item.vue"),
                meta: {requireAuth: true}
            },
            {
                path: "/dimension",
                name: "dimension",
                component: () => import( "./views/dimension/dimension.vue"),
                meta: {requireAuth: true}
            },
            {
                path: "/role",
                name: "role",
                component: () => import( "./views/role/role.vue"),
                meta: {requireAuth: true}
            },
            {
                path: "/user",
                name: "user",
                component: () => import( "./views/user/user.vue"),
                meta: {requireAuth: true}
            },
            {
                path: "*",
                redirect: "/home",
            }
        ]
    });
};
