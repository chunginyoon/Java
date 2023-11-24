<script setup>
defineProps({ article: Object });
</script>

<template>
  <tr class="text-center">
    <th scope="row">{{ article.articleNo }}</th>
    <td class="text-start">
      <router-link
        class="article-title link-dark"
        style="text-decoration: none"
        :to="{ name: 'article-view', params: { articleno: article.articleNo } }"
      >
        {{ article.subject }}
      </router-link>
      <!-- <a
        href="#"
        class="article-title link-dark"
        :data-no="article.articleNo"
        style="text-decoration: none"
      >
        {{ article.subject }}
      </a> -->
    </td>
    <td>{{ article.userName }}</td>
    <td>{{ article.hit }}</td>
    <td>{{ article.registerTime }}</td>
  </tr>
</template>

<style scoped></style>
