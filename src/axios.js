import axios from 'axios';

let API_URL = 'http://localhost:8080/api/movies/movies';


const postCategorias = async(genres) =>{
    try{
        const response = await fetch('http://localhost:8080/api/movies/genres', {
       method: 'POST',
       body: JSON.stringify(usuarios),
       headers: { 'Content-type': 'application/json; charset=UTF-8' },
     });
     
     const usuarioCreado = await response.json();
     this.usuarios = [...this.usuarios, usuarioCreado];
   } catch (error) {
     console.error(error);
   }
    
}

const  postPeliculas = async (movies) => {
   try {
     const response = await fetch('http://localhost:8080/api/movies/movies', {
       method: 'POST',
       body: JSON.stringify(usuarios),
       headers: { 'Content-type': 'application/json; charset=UTF-8' },
     });
     
     const usuarioCreado = await response.json();
     this.usuarios = [...this.usuarios, usuarioCreado];
   } catch (error) {
     console.error(error);
   }
 }

export default {
   obtenerPersonasPaginadas
};