var Options = {
    options: {
        methods: ["card"],
        methods_disabled: [],
        full_screen: false,
        title: "Example title.",
        active_tab: 'card',
        theme: {
            type: "light",
            preset: "black"
        },
    },
    params: {
        merchant_id: 1396424,
        currency: "EUR",
        order_id: new Date().getTime(),
        amount: 5000,
        order_desc: "Test payment"
    }
};
fondy("#checkout-container", Options);