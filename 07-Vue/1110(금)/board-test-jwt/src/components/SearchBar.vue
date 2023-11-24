<script setup>
import { ref } from "vue";
const props = defineProps(["options"]);

const searchInfo = ref({
    key: "",
    word: "",
});
</script>

<template>
    <div>
        <form @submit.prevent="$emit('searchEvent', searchInfo)">
            <select v-model="searchInfo.key">
                <option value="" disabled>--선택--</option>
                <option v-for="option in props.options" :key="option.value" :value="option.value">
                    {{ option.text }}
                </option>
            </select>
            <input type="text" v-model="searchInfo.word" @input="$emit('searchEvent', searchInfo)" />
            <button>검색</button>
        </form>
    </div>
</template>

<style scoped></style>
