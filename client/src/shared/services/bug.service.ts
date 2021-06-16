import axios, { AxiosResponse } from 'axios';
import { Bug } from '../models/Bug';

export const findAllBugs = async (): Promise<Bug[]> => {
    return axios.get('http://localhost:8080/bugs').then((response: AxiosResponse<Bug[]>) => response.data);
};