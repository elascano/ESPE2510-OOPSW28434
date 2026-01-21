import { FrmMenu } from './src/view/FrmMenu.js';
import { FrmPayRent } from './src/view/FrmPayRent.js';
import { FrmModifyRent } from './src/view/FrmModifyRent.js';

const routes = {
    'menu': FrmMenu,
    'pay': FrmPayRent,
    'modify': FrmModifyRent
};

window.router = {
    navigate: (route) => {
        const view = routes[route];
        const app = document.getElementById('app');
        app.innerHTML = view.render();
        if (view.init) view.init();
    }
};

window.onload = () => window.router.navigate('menu');