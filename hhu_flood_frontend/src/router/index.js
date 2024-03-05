import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import earth from "../views/earth";
import  ForecastContainer from "../components/Forecast/ForecastContainer";
import  AnaResult from "../components/Forecast/AnaResult";
import  OutPutResult from "../components/Forecast/OutPutResult";
import  para from "../components/Forecast/para";
import AutoCal from "../components/Forecast/AutoCal";

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'earth',
      component: earth,
      children:[
        {
          path:'forecast',
          name: 'forecast',
          component:ForecastContainer,
          children:[
            {
              path:'anaResult',
              component: AnaResult,
            },
            {
              path:'outPutResult',
              component: OutPutResult,
            },
            {
              path:'para',
              component: para,
            },
            {
              path:'AutoCal',
              component: AutoCal,
            },
          ]
        },
      ]
    },

  ]
})
