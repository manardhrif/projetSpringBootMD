import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OrdreFabrication } from '../models/ordre-fabrication';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrdreFabricationService {
  private apiUrl = 'http://localhost:8080/api/ordres'; // adapte à ton endpoint

  constructor(private http: HttpClient) {}

  getAll(): Observable<OrdreFabrication[]> {
    return this.http.get<OrdreFabrication[]>(this.apiUrl);
  }

  add(ord: OrdreFabrication): Observable<OrdreFabrication> {
    return this.http.post<OrdreFabrication>(this.apiUrl, ord);
  }

  update(ord: OrdreFabrication): Observable<OrdreFabrication> {
    return this.http.put<OrdreFabrication>(`${this.apiUrl}/${ord.id}`, ord);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getById(id: number): Observable<OrdreFabrication> {
    return this.http.get<OrdreFabrication>(`${this.apiUrl}/${id}`);
  }
}
