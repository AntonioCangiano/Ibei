package org.elis.ibei.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.elis.ibei.model.Ruolo;
import org.elis.ibei.model.Utente;
import org.elis.ibei.service.def.UtenteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class GestoreTokenService {
    private final UtenteService service;

    @Value("${mia.chiave.per.jwt}")
    private  String testoPerLaChiave;

    private SecretKey getsecretKey(){
        return Keys.hmacShaKeyFor(testoPerLaChiave.getBytes());
    }

    public String createToken(Utente u){
        long dieciGiorni=1000L*60*60*24*10;
        return Jwts
                //apro il builder per creare il mio token
                    .builder()
                    //inizio ad inserire i dati nel payload
                        .claims()
                        //setto tutti i dato
                            .subject(u.getEmail())
                            .issuedAt(new Date(System.currentTimeMillis()))
                            .expiration(new Date(System.currentTimeMillis()+dieciGiorni))
                            .add("raffaele","mozzarelle nel frigorifero")
                            .add("ruolo",u.getRuolo())
                            //ho finito di inserire i dati
                        .and()
                        //firmo il token con  la mia chiave segreta
                .signWith(getsecretKey())
                //creo finalmente il token
            .compact();
    }

    private Claims getClaims(String token){
        if(token==null)throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        if(token.startsWith("Bearer ")){
            token = token.substring(7);
        }
        try {
            Claims c = Jwts.parser()
                    .verifyWith(getsecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
                    return c;
        }catch (JwtException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
    public Date getCreationDate(String token){
        return getClaims(token).getIssuedAt();
    }
    public Ruolo getRuolo(String token){
        return (Ruolo)getClaims(token).get("ruolo");
    }
    public LocalDateTime getExpiration(String token){
        Date d = getClaims(token).getExpiration();
        return LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
    }
    public Utente getUtente(String token){
        String email = getClaims(token).getSubject();
        Utente u=service.getbyEmail(email);
        return u;
    }
}

