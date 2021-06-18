import axios, { AxiosResponse } from 'axios';
import { BugPayload } from '../models/BugPayload';

export const findAllBugs = async (): Promise<BugPayload[]> => {
    return axios.get('/bug').then((response: AxiosResponse<BugPayload[]>) => response.data);
};

export const findBugById = async (id: number): Promise<BugPayload> => {
    return axios.get(`/bug/${id}`).then((response: AxiosResponse<BugPayload>) => response.data);
};
