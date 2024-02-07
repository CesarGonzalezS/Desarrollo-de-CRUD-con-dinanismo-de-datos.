<template>
    <div>
        <Cardpeliculas />

        <div>
            <b-button @click="openModal">Launch demo modal</b-button>

            <b-modal id="modal-1" title="Registro de películas" v-model="showModal">
                <b-form @submit="onSubmit" @reset="onReset">
                    <b-form-group id="input-group-1" label="Nombre de película:" label-for="input-1"
                        description="Registra el nombre latinoamericano.">
                        <b-form-input id="input-1" v-model="form.name" type="text"
                            placeholder="Ingresa el nombre de la película." required></b-form-input>
                    </b-form-group>



                    <b-form-input id="input-1" v-model="form.year" type="number"
                        placeholder="Ingresa el ano de la pelicula" required></b-form-input>
                    

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
import Cardpeliculas from './cardpeliculas.vue';
import carrusel from './cardpeliculas.vue';
export default {
    components: { carrusel, Cardpeliculas },
    data() {
        return {
            form: {
                "id": 1,
                "name": "Inception",
                "genre": {
                    "id": 1,
                    "name": "Action"
                },
                "year": 2010
            },

            genres: {


                "id": 1,
                "name": "Action"

            },
            showModal: false
        };
    },
    mounted() {
        this.movies = this.movies.map((movie) => {
            return { text: movie, value: movie }
        })
        this.genres = this.genres.map((genre) => {
            return { text: genre, value: genre }
        })
    },

    methods: {

        openModal() {

            this.showModal = true;
        },
        onSubmit(evt) {
            evt.preventDefault();
            alert('¡Registro completado!');
        },
        onReset(evt) {
            evt.preventDefault();
            this.form.name = '';
            this.form.genre = null;
            this.showModal = false;
        }
    }
};


</script>

<script>
</script>
<style></style>
  