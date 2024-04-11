package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "gk_card_reader")
public class CardReader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "card_reader_name")
    private String card_reader_name;
    @Column(name = "port")
    private int port;
    @Column(name = "protocol")
    private String protocol;
    @Column
    private int parent_id;
    public CardReader(){}
    public CardReader(String card_reader_name, int port, String protocol, int parent_id) {
        this.card_reader_name = card_reader_name;
        this.port = port;
        this.protocol = protocol;
        this.parent_id = parent_id;
    }
}
