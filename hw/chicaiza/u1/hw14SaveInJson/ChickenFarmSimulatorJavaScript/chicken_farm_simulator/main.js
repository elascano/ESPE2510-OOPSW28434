#!/usr/bin/env node

import { FarmController } from './src/controller/FarmController.js';
import { FarmView } from './src/view/FarmView.js';

async function main() {
    try {
        const controller = new FarmController();
        const view = new FarmView(controller);
        await view.run();
    } catch (error) {
        console.log("Application error:", error.message);
        process.exit(1);
    }
}

main().catch(console.error);