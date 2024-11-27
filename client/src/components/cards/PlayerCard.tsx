import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Player } from "@/types/player"

interface PlayerCardProps {
    player: Player;
}

export function PlayerCard({ player }: PlayerCardProps) {
    return (
        <Card className="h-full">
            <CardHeader>
                <CardTitle className="flex justify-between items-center">
                    <span>{player.username}</span>
                    <Badge>{player.position}</Badge>
                </CardTitle>
            </CardHeader>
            <CardContent>
                <dl className="grid grid-cols-2 gap-2 text-sm">
                    <dt className="font-semibold">Age:</dt>
                    <dd>{player.age}</dd>
                    <dt className="font-semibold">Started Playing:</dt>
                    <dd>{player.startedPlaying}</dd>
                    <dt className="font-semibold">Club:</dt>
                    <dd>{player.club}</dd>
                    <dt className="font-semibold">Email:</dt>
                    <dd className="truncate">{player.email}</dd>
                </dl>
            </CardContent>
        </Card>
    )
}

