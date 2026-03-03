import { axiosInstance } from './axiosInstance';
import type { Heroe } from './heroes';

export interface Equipo {
    id: number;
    nombre: string;
    heroes: Heroe[];
}

export const getEquipos = async () => {
    const response = await axiosInstance.get<Equipo[]>('/equipos');
    return response.data;
};

export const getEquipoById = async (id: number) => {
    const response = await axiosInstance.get<Equipo>(`/equipos/${id}`);
    return response.data;
};

export const createEquipo = async (equipo: { nombre: string }) => {
    const response = await axiosInstance.post<Equipo>('/equipos', equipo);
    return response.data;
};

export const deleteEquipo = async (id: number) => {
    await axiosInstance.delete(`/equipos/${id}`);
};
