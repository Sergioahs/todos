function main() {
	
	cargarTodos();

	let btn = document.querySelector('.btn');
	btn.addEventListener('click', (e) => {
		let nameInput = document.querySelector('div.info > input');
		let descriptionArea = document.querySelector('div.info > textarea');

		let postWraper = document.querySelector('.post-wraper');
		let postCard = postWraper.querySelector('.post-card');

		postWraper.innerHTML = "Enviando todo...";
		let datos = {
			nombre: nameInput.value,
			descripcion: descriptionArea.value
		}

		fetch("/todo", {
			method: 'POST',
			body: JSON.stringify(datos)
		})
		.then((response) => {
			if(response.ok) {
				cargarTodos();
				postWraper.innerHTML = "";
				postWraper.appendChild(postCard);
			}
		})
		.catch((e) => {
			console.log(e);
		});
	});

	function cargarTodos() {

		let todosWraper = document.querySelector('.todos-wraper');

		fetch("/todo")
		.then((response) => {
			return response.json();
		})
		.then((json) => {
			let tarjetas = json.map((tarjeta) => plantillaTarjeta(tarjeta));
			todosWraper.innerHTML = tarjetas.join("");

			let botonesBorrar = document.querySelectorAll('.borrar');
			botonesBorrar.forEach( function(unBoton) {
				unBoton.addEventListener('click', (e) => {
					let tarjetaTodo = e.target.parentNode.parentNode;
					let id = tarjetaTodo.querySelector('.id').textContent;
					todosWraper.innerHTML = "Eliminando elemento...";
					fetch(`/todo/${ id }`, {
						method: 'DELETE'
					})
					.then((response) => {
						if(response.ok) {
							cargarTodos();
						}
					});
				});
			});

			let botonesEditar = document.querySelectorAll('.editar');
			botonesEditar.forEach( function(unBoton) {
				unBoton.addEventListener('click', (e) => {
					let tarjetaTodo = e.target.parentNode.parentNode;
					todosWraper.innerHTML = plantillaEditar({
						id: tarjetaTodo.querySelector('div.id').textContent,
						nombre: tarjetaTodo.querySelector('h1.nombre').textContent,
						descripcion: tarjetaTodo.querySelector('div.ds').textContent
					});

					let btn = document.getElementById('actualizar');
					btn.addEventListener('click', (e) => {

						let tarjeta = e.target.parentNode.parentNode;
						let data = {
							id: tarjeta.querySelector('div.id').textContent,
							nombre: tarjeta.querySelector('input.nombre').value,
							descripcion: tarjeta.querySelector('textarea.descripcion').value
						}

						console.log(data);

						todosWraper.innerHTML = "Actualizando elemento...";
						
						fetch("/todo", {
							method: 'PUT',
							body: JSON.stringify(data)
						})
						.then((response) => {
							if(response.ok) {
								cargarTodos();
							}
						});
					});
				});
			});
		});
	}

	/*  Plantillas  */
	function plantillaTarjeta(datos) {
		return  `
			<div class="data-card">
				<div class="id">${ datos.id }</div>
				<div class="info">
					<h1 class="nombre center">${ datos.nombre }</h1>
					<div class="info-container center">
						<div class="ds width-100 size-1-5">
							${ datos.descripcion }
						</div>
					</div>
				</div>
				<div class="btn-wraper center">
					<div class="editar center">Editar</div>
					<div class="borrar center">Borrar</div>
				</div>
			</div>
		`;
	}

	function plantillaEditar(datos) {
		console.log(datos);
		return  `
			<div class="data-card">
				<div class="info">
					<div class="id">${ datos.id }</div>
					<h1 class="center">Todo</h1>
					<input type="text" name="nombre" class="nombre width-100 align-center size-1-5" placeholder="Nombre del todo" value="${ datos.nombre }">
					<textarea class="descripcion width-100 align-center size-1-5" placeholder="Descripcion del todo">${ datos.descripcion }</textarea>
				</div>
				<div class="btn-wraper center">
					<div class="btn center" id="actualizar">Actualizar</div>
				</div>
			</div>
		`;
	}
}

window.addEventListener('load', main);