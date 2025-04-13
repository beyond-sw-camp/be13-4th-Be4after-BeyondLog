import {createRouter, createWebHistory} from "vue-router";

const Home = () => import("@/views/Home.vue");
const QnAList = () => import("@/views/QnA/QnAList.vue");
const QnAadd = () => import("@/views/QnA/QnAadd.vue");
const QnADetail = () => import("@/views/QnA/QnADetail.vue");
const BaseLayout = () => import("@/components/layout/BaseLayout.vue");
const Login = () => import('@/views/Login.vue')

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "base",
            component: BaseLayout,
            children: [
                {path: "", name: "home", component: Home},
                {path: "qna", name: "qna", component: QnAList},
                {path: "qna/add", name: "qnaAdd", component: QnAadd},
                {path: "qna/:id", name: "qnaDetail", component: QnADetail},
            ],
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        }
    ],
});

router.beforeEach((to, form, next) => {
    next();
});

export default router;
