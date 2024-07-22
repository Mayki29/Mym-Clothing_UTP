// server.js
const express = require('express');
const Stripe = require('stripe');
const bodyParser = require('body-parser');
const cors = require('cors');

const stripe = Stripe('sk_test_51PbtkaLx8S7JHdc9JDpmhOVHNlEbdVAKWRBNmTfrjKyibQCSe6yUCipRxPzBe7xonlNcxzCk5iVNNz4ZBK0v0cj700eDUT11SN');

const app = express();

app.use(bodyParser.json());
app.use(cors());

// Endpoint para crear la sesión de pago
app.post('/create-checkout-session', async (req, res) => {
    const { items } = req.body;

    const lineItems = items.map(item => ({
        price_data: {
            currency: 'pen',
            product_data: {
                name: item.name,
            },
            unit_amount: item.price * 100, // Convertir a centavos
        },
        quantity: item.quantity,
    }));

    const session = await stripe.checkout.sessions.create({
        payment_method_types: ['card'],
        line_items: lineItems,
        mode: 'payment',
        success_url: 'http://localhost:3000/success',
        cancel_url: 'http://localhost:3000/cancel',
    });

    res.json({ id: session.id });
});

const PORT = process.env.PORT || 4242;

app.listen(PORT, () => console.log(`Server is running on port ${PORT}`));
