export const FrmMenu = {
    render: () => `
        <div class="frame">
            <nav class="menu-bar">Options</nav>
            <h1 class="perpetua-title">WELCOME TO THE SYSTEM</h1>
            <div class="menu-actions">
                <button onclick="window.router.navigate('pay')">Pay Rent</button>
                <button onclick="window.router.navigate('modify')">Modify Rent</button>
            </div>
        </div>
    `
};