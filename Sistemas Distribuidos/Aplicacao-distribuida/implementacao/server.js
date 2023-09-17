
//cria um novo servidor HTTP e o anexa ao servidor HTTP
const app = require('express')()
const server = require('http').createServer(app)

//cria um novo servidor Socket.IO e o anexa ao servidor HTTP
const io = require('socket.io')(server, {cors: {origin: 'http://localhost:5173'}})

//define a porta 
const PORT = 3001

//define o manipulador de eventos para o evento connection
//que é disparado quando um cliente se conecta ao servidor
io.on('connection', socket => {
  console.log('Usuário conectado!', socket.id);

  socket.on('disconnect', reason => {
    console.log('Usuário desconectado!', socket.id)
  })

  socket.on('set_username', username => {
    socket.data.username = username
  })

  socket.on('message', text => {
    io.emit('receive_message', {
      text,
      authorId: socket.id,
      author: socket.data.username
    })
  })
})

//inicia o servidor na porta definida
server.listen(PORT, () => console.log('Server running...'))
