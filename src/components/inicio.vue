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
                        <b-form-select v-model="form.genre" :options="genres" required>
                            <template v-slot:first>
                                <option :value="0">Selecciona un género</option>
                            </template>
                        </b-form-select>
                        <div v-if="genres.length === 0">Loading genres...</div>
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
            genresToSend: [],
            showModal: false
        };
    },
    mounted() {
        // Llamar a la API para obtener los géneros disponibles
        this.fetchGenres();
        //set default value 0
        this.form.genre = 0;

    },
    methods: {
        openModal() {
            this.showModal = true;
        },
        onSubmit(evt) {
            // Enviar la película a la API
            this.registerMovie();
        },
        onReset(evt) {
            this.form.name = '';
            this.form.genre = '';
            this.form.year = '';
            this.showModal = false;
        },
        async fetchGenres() {
            try {
                const response = await axios.get('http://localhost:8080/api/movies/genres');

                this.genres = response.data.map(genre => ({ text: genre.name, value: genre.id }));
                this.genresToSend = response.data;
            } catch (error) {
                console.error('Error al obtener los géneros:', error);
                this.errorFetchingGenres = 'Error al obtener los géneros. Por favor, inténtelo de nuevo.';
            }
        },
        async registerMovie() {
            try {
                if (this.form.genre === 0) {
                    alert('Por favor, selecciona un género.');
                    return;
                }
                //buscar el id del genero y enviar el objeto completo
                this.form.genre = this.genresToSend.find(genre => genre.id === this.form.genre);
                console.log(this.form.genre);

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
