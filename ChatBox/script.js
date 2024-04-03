function sendMessage() {
    var userInput = document.getElementById('user-input').value;
    var chatBox = document.getElementById('chat-box');


    var userMessageElement = document.createElement('div');
    userMessageElement.classList.add('chat-message', 'user-message');
    userMessageElement.textContent = userInput;
    chatBox.appendChild(userMessageElement);

    
    var botResponse = generateBotResponse(userInput);

    
    var botMessageElement = document.createElement('div');
    botMessageElement.classList.add('chat-message', 'bot-message');
    botMessageElement.textContent = botResponse;
    chatBox.appendChild(botMessageElement);

    
    chatBox.scrollTop = chatBox.scrollHeight;

    // Clear user input field
    document.getElementById('user-input').value = '';
}

function generateBotResponse(userInput) {

    const responses = {
        'hello': 'Hello there!',
        'hi' : 'Hello there',
        'hey': 'Hello! How can i help you today?',
        'what is the capital city of South Africa': 'The capital of South Africa is Pretoria',
        'what is the capital city of South Africa':'The capital of South Africa is Pretoria',
        'what is the official language of South Africa': 'South Africa has 11 official languages, namely: isiZulu, isiXhosa, Afrikaans, English, Sepedi, Setswana, Sesotho, Xitsonga, siSwati, Tshivenda, and isiNdebele.',
        'how are you': 'Doing great, and ready to help. How can I assist you?',
        'how are good': 'Doing great, and ready to help. How can I assist you?',
        'what is html': 'HTML, which stands for HyperText Markup Language, is the standard markup language used to create and design web pages. It provides the structure and layout of content on a web page by using a system of tags and attributes.',
        'what is JavaScript': 'JavaScript is a versatile programming language primarily used for creating interactive effects within web browsers. Initially developed by Brendan Eich at Netscape Communications Corporation, JavaScript has evolved into one of the most popular programming languages in the world.',
        'bye': 'Goodbye! Have a great day!',
    };

    // handle longer sentences'
    const userTokens = userInput.toLowerCase().split(' ');

    // Check if userInput matches any predefined rule
    for (var key in responses) {
        const responseTokens = key.toLowerCase().split(' ');

        if (responseTokens.every(token => userTokens.includes(token))) {
            return responses[key];
        }
    }

    return 'I am sorry, I did not understand that.';
}
