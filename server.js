const express = require('express');
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.json());

// User data storage (in-memory array)
const users = [];

app.post('/api/signup', (req, res) => {
    const username = req.body.username;
    const password = req.body.password;

    // Check if the username is already taken
    const existingUser = users.find(user => user.username === username);
    if (existingUser) {
        return res.status(400).json({ message: 'Username already exists' });
    }

    // Store the user data
    const newUser = { username, password };
    users.push(newUser);

    res.status(200).json({ message: 'Signup successful' });
});

app.post('/api/login', (req, res) => {
    const username = req.body.username;
    const password = req.body.password;

    // Find the user by username
    const user = users.find(user => user.username === username);

    if (!user) {
        return res.status(401).json({ message: 'Invalid username or password' });
    }

    // Check the password
    if (user.password !== password) {
        return res.status(401).json({ message: 'Invalid username or password' });
    }

    res.status(200).json({ message: 'Login successful' });
});

app.listen(3000, () => {
    console.log('Server started on port 3000');
});
