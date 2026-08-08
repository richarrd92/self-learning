import { Client, Databases } from 'appwrite';

// create client to interact with Appwrite project note app 
export const client = new Client()
    .setEndpoint('https://cloud.appwrite.io/v1')
    .setProject('677afab000101879b0b2')

// using client connection - interact with database
export const databases = new Databases(client)