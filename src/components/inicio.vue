<template>
    <div>
        <Cardpeliculas />

        <div>
            <b-button @click="openModal">Registrar película</b-button>

            <b-modal id="modal-1" title="Registro de películas" v-model="showModal">
                <b-form @submit="onSubmit" @reset="onReset">
                    <b-form-group id="input-group-1" label="Nombre de película:" label-for="input-1"
                        description="Registra el nombre latinoamericano.">
                        <b-form-input id="input-1" v-model="form.name" type="text"
                            placeholder="Ingresa el nombre de la película." required></b-form-input>
                    </b-form-group>

                    <b-form-input id="input-2" v-model="form.year" type="number" required
                        placeholder="Ingresa el año de la película"></b-form-input>

                    <b-form-group id="input-group-4" label="Género" label-for="input-3">
                        <b-form-select id="input-3" v-model="form.genre" :options="genres" required></b-form-select>
                    </b-form-group>

                    <b-button type="submit" variant="primary">Registrar</b-button>
                </b-form>
            </b-modal>
        </div>

    </div>
</template>
  
<script>
import axios from 'axios';
import Cardpeliculas from './cardpeliculas.vue'; // Si es necesario
// import carrusel from './carrusel.vue'; // Si es necesario

export default {
    components: { Cardpeliculas },
    data() {
        return {
            form: {
                name: '',
                genre: '',
                year: ''
            },
            genres: [],
            showModal: false
        };
    },
    mounted() {
        // Llamar a la API para obtener los géneros disponibles
        this.fetchGenres();
    },
    methods: {
        openModal() {
            this.showModal = true;
        },
        onSubmit(evt) {
            evt.preventDefault();
            // Enviar la película a la API
            this.registerMovie();
        },
        onReset(evt) {
            evt.preventDefault();
            this.form.name = '';
            this.form.genre = '';
            this.form.year = '';
            this.showModal = false;
        },
        async fetchGenres() {
            try {
                const response = await axios.get('http://localhost:8080/api/movies/genres');
                this.genres = response.data;
            } catch (error) {
                console.error('Error al obtener los géneros:', error);
            }
        },
        async registerMovie() {
            try {
                await axios.post('http://localhost:8080/api/movies/movies', this.form);
                alert('¡Película registrada con éxito!');
                this.onReset(); // Limpiar el formulario después de registrar la película
            } catch (error) {
                console.error('Error al registrar la película:', error);
            }
        }
    }
};
</script>

<style scoped>
/* Estilos adicionales según sea necesario */
</style>
